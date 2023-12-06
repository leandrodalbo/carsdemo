import { useQuery } from "@tanstack/react-query";
import { CarType } from "../Api";
import { CarService } from "../service/CarService";

interface CarListProps {
  service: CarService;
}
const CarList = (props: CarListProps) => {
  const { service } = props;

  const { data, error, isSuccess } = useQuery({
    queryKey: ["allcars"],
    queryFn: service.fetchAll,
  });

  if (!isSuccess) return <span>Loading...</span>;
  if (error) return <span>Fetching Cars Error...</span>;
  if (data)
    return (
      <table>
        <tbody>
          {data.map((car: CarType) => (
            <tr key={car.carId}>
              <td>{car.carId}</td>
              <td>{car.brand}</td>
              <td>{car.model}</td>
              <td>{car.color}</td>
              <td>{car.registrationNumber}</td>
              <td>{car.modelYear}</td>
              <td>{car.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
};
export default CarList;
