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
        <DialogContent>
          <input
            placeholder="Brand"
            name="brand"
            value={car.brand}
            onChange={onCarChange}
          />
          <br />

          <input
            placeholder="Model"
            name="model"
            value={car.model}
            onChange={onCarChange}
          />
          <br />

          <input
            placeholder="Color"
            name="color"
            value={car.color}
            onChange={onCarChange}
          />
          <br />

          <input
            placeholder="Registration Number"
            name="registrationNumber"
            value={car.registrationNumber}
            onChange={onCarChange}
          />
          <br />

          <input
            placeholder="Model Year"
            name="modelYear"
            value={car.modelYear}
            onChange={onCarChange}
          />
          <br />

          <input
            placeholder="Price"
            name="price"
            value={car.price}
            onChange={onCarChange}
          />
          <br />
        </DialogContent>
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
