import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { CarService } from "../service/CarService";
import { DataGrid, GridCellParams, GridColDef } from "@mui/x-data-grid";
import AddCar from "../addCar/AddCar";
import EditCar from "../editCar/EditCar";
import { Car } from "../Api";

interface CarListProps {
  service: CarService;
}

const CarList = (props: CarListProps) => {
  const { service } = props;

  const { data, error, isSuccess } = useQuery({
    queryKey: ["allcars"],
    queryFn: service.fetchAll,
  });

  const queryClient = useQueryClient();

  const { mutate } = useMutation(service.delete, {
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["allcars"] }),
  });

  const columns: GridColDef[] = [
    { field: "brand", headerName: "Brand", width: 200 },
    { field: "model", headerName: "Model", width: 200 },
    { field: "color", headerName: "Color", width: 200 },
    { field: "registrationNumber", headerName: "Reg.nr.", width: 150 },
    { field: "modelYear", headerName: "Model Year", width: 150 },
    { field: "price", headerName: "Price", width: 150 },
    {
      field: "delete",
      headerName: "",
      width: 90,
      sortable: false,
      filterable: false,
      disableColumnMenu: true,
      renderCell: (params: GridCellParams) => (
        <button onClick={() => mutate(params.row.carId)}>Delete</button>
      ),
    },

    {
      field: "edit",
      headerName: "",
      width: 90,
      sortable: false,
      filterable: false,
      disableColumnMenu: true,
      renderCell: (params: GridCellParams) => (
        <EditCar
          service={service}
          queryClient={queryClient}
          editiableCar={
            {
              carId: params.row.carId,
              brand: params.row.brand,
              model: params.row.model,
              color: params.row.color,
              registrationNumber: params.row.registrationNumber,
              modelYear: params.row.modelYear,
              price: params.row.price,
            } as Car
          }
        />
      ),
    },
  ];

  if (!isSuccess) return <span>Loading...</span>;
  if (error) return <span>Fetching Cars Error...</span>;
  if (data)
    return (
      <>
        <AddCar service={service} queryClient={queryClient} />
        <DataGrid rows={data} columns={columns} getRowId={(row) => row.carId} />
      </>
    );
};
export default CarList;
