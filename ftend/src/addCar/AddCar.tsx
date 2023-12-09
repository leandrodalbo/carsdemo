import React, { useState } from "react";
import { Car } from "../Api";
import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";

import { QueryClient, useMutation } from "@tanstack/react-query";
import { CarService } from "../service/CarService";
import CarDialogContent from "../carDialogContent/CarDialogContent";

interface AddCarProps {
  service: CarService;
  queryClient: QueryClient;
}

const AddCar = (props: AddCarProps) => {
  const { service, queryClient } = props;
  const [dialogState, setDialogState] = useState(false);

  const [car, setCar] = useState<Car>({
    brand: "",
    model: "",
    color: "",
    registrationNumber: "",
    modelYear: 0,
    price: 0,
  });

  const openDialog = () => setDialogState(true);
  const closeDialog = () => setDialogState(false);

  const onCarChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCar({ ...car, [event.target.name]: event.target.value });
  };

  const { mutate } = useMutation(service.newCar, {
    onSuccess: () => {
      setCar({
        brand: "",
        model: "",
        color: "",
        registrationNumber: "",
        modelYear: 0,
        price: 0,
      });
      closeDialog();
      queryClient.invalidateQueries({ queryKey: ["allcars"] });
    },
  });

  return (
    <>
      <button onClick={openDialog}>Add Car</button>
      <Dialog open={dialogState} onClose={closeDialog}>
        <DialogTitle>Create Car</DialogTitle>
        <CarDialogContent car={car} onCarChange={onCarChange} />
        <DialogActions>
          <button onClick={closeDialog}>Cancel</button>
          <button
            onClick={() => {
              mutate(car);
            }}
          >
            Save
          </button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default AddCar;
