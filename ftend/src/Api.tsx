export const apiurl = import.meta.env.VITE_API_URL;

export interface Car {
  brand: string;
  model: string;
  color: string;
  registrationNumber: string;
  modelYear: number;
  price: number;
}
