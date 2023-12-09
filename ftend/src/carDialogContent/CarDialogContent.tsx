import { DialogContent } from "@mui/material";
import { Car } from "../Api";

interface CarDialogContentProps {
  onCarChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  car: Car;
}

const CarDialogContent = (props: CarDialogContentProps) => {
  const { car, onCarChange } = props;
  return (
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
  );
};

export default CarDialogContent;
