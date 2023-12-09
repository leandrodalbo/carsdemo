import { useState } from "react";
import { Car } from "../Api";
import { CarService } from "../service/CarService";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import { QueryClient, useMutation } from "@tanstack/react-query";
import CarDialogContent from "../carDialogContent/CarDialogContent";

interface EditCarProps {
  service: CarService;
  editiableCar: Car;
  queryClient: QueryClient;
}

const EditCar = (props: EditCarProps) => {
  const { editiableCar, service, queryClient } = props;

  const [dialogState, setDialogState] = useState(false);
  const [car, setCar] = useState<Car>(editiableCar);

  const openDialog = () => setDialogState(true);
  const closeDialog = () => setDialogState(false);

  const onCarChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCar({ ...car, [event.target.name]: event.target.value });
  };

  const { mutate } = useMutation(service.updateCar, {
    onSuccess: () => {
      closeDialog();
      queryClient.invalidateQueries({ queryKey: ["allcars"] });
    },
  });

  return (
    <>
      <button onClick={openDialog}>Edit</button>
      <Dialog open={dialogState} onClose={closeDialog}>
        <DialogTitle>Edit Car</DialogTitle>
        <CarDialogContent car={car} onCarChange={onCarChange} />
        <DialogActions>
          <button onClick={closeDialog}>Cancel</button>
          <button onClick={() => mutate(car)}>Save</button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default EditCar;
