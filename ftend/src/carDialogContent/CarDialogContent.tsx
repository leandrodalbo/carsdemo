import { DialogContent, Stack, TextField } from "@mui/material";
import { Car } from "../Api";

interface CarDialogContentProps {
  onCarChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  car: Car;
}

const CarDialogContent = (props: CarDialogContentProps) => {
  const { car, onCarChange } = props;
  return (
    <DialogContent>
      <Stack spacing={1} mt={1}>
        <TextField
          label="Brand"
          name="brand"
          value={car.brand}
          onChange={onCarChange}
        />

        <TextField
          label="Model"
          name="model"
          value={car.model}
          onChange={onCarChange}
        />

        <TextField
          label="Color"
          name="color"
          value={car.color}
          onChange={onCarChange}
        />

        <TextField
          label="Registration Number"
          name="registrationNumber"
          value={car.registrationNumber}
          onChange={onCarChange}
        />

        <TextField
          label="Model Year"
          name="modelYear"
          value={car.modelYear}
          onChange={onCarChange}
        />

        <TextField
          label="Price"
          name="price"
          value={car.price}
          onChange={onCarChange}
        />
      </Stack>
    </DialogContent>
  );
};

export default CarDialogContent;
