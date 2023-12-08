import { Car, apiurl } from "../Api";
import axios from "axios";

export class CarService {
  async fetchAll() {
    const response = await axios.get(`${apiurl}/cars/all`);

    return response.data;
  }

  async delete(carId: number) {
    const response = await axios.delete(`${apiurl}/cars/${carId}`);

    return response.status;
  }

  async newCar(car: Car) {
    const response = await axios.post(`${apiurl}/cars`, car, {
      headers: {
        "Content-Type": "application/json",
      },
    });

    return response.status;
  }
}

const carService = new CarService();

export default carService;
