export type CarType = {
  carId: number;
  brand: string;
  model: string;
  color: string;
  registrationNumber: string;
  modelYear: number;
  price: number;
};

export const apiurl = import.meta.env.VITE_API_URL;
