import {
  AppBar,
  Container,
  CssBaseline,
  Toolbar,
  Typography,
} from "@mui/material";

import { QueryClientProvider, QueryClient } from "@tanstack/react-query";
import CarList from "./carsList/CarList";
import carService from "./service/CarService";

const queryClient = new QueryClient();

const App = () => (
  <Container maxWidth="xl">
    <CssBaseline />
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6">Cars Demo</Typography>
      </Toolbar>
    </AppBar>
    <QueryClientProvider client={queryClient}>
      <CarList service={carService} />
    </QueryClientProvider>
  </Container>
);

export default App;
