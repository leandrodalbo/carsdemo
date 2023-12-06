import { apiurl } from "../Api";
import axios from "axios";

export class CarService {
  async fetchAll() {
    const response = await axios.get(`${apiurl}/cars/all`);

    return response.data;
  }
}

const carService = new CarService();

export default carService;
