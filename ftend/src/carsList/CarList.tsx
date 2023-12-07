import { useQuery } from "@tanstack/react-query";
import { CarService } from "../service/CarService";
import { DataGrid, GridColDef } from "@mui/x-data-grid";

interface CarListProps {
  service: CarService;
}

const CarList = (props: CarListProps) => {
  const { service } = props;

  const { data, error, isSuccess } = useQuery({
    queryKey: ["allcars"],
    queryFn: service.fetchAll,
  });

  const columns: GridColDef[] = [
    { field: "brand", headerName: "Brand", width: 200 },
    { field: "model", headerName: "Model", width: 200 },
    { field: "color", headerName: "Color", width: 200 },
    { field: "registrationNumber", headerName: "Reg.nr.", width: 150 },
    { field: "modelYear", headerName: "Model Year", width: 150 },
    { field: "price", headerName: "Price", width: 150 },
  ];

  if (!isSuccess) return <span>Loading...</span>;
  if (error) return <span>Fetching Cars Error...</span>;
  if (data)
    return (
      <DataGrid rows={data} columns={columns} getRowId={(row) => row.carId} />
    );
};
export default CarList;
