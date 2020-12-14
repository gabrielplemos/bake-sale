import React from 'react';
import FileIcon from '@material-ui/icons/InsertDriveFile';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import './home.css';
import { loadItems } from '../../services/bake-sale-service';
import Papa from 'papaparse';
import { useHistory } from "react-router-dom";


const onChange = (event: any, history: any) => {
  
    Papa.parse(event.target.files[0], {
        header: true,
        complete: async result =>  {
            const bakeSaleId = await loadItems(result.data);
            history.push(`/${bakeSaleId}`)
        }
    });
}

function Home() {
  const history = useHistory();
  return (
    <div className="container">
        <Typography
                  component="span"
                  variant="h4"
                  color="textPrimary"
            >Welcome to the Bake Sale!</Typography>
          <Box display="flex" p={1} flexDirection="row" alignItems="center">
        <Box p={1}>
        <Typography
                  component="span"
                  variant="body2"
                  color="textPrimary"
            >Upload the file</Typography>
        </Box>
        <Box p={1}>
        <input
          color="primary"
          type="file"
          onChange={event => onChange(event, history)}
          id="icon-button-file"
          style={{ display: 'none', }}
        />
        <label htmlFor="icon-button-file">
          <Button
            variant="contained"
            component="span"
            size="small"
            color="primary"
          >
            <FileIcon  />
          </Button>
        </label>
        </Box>
        </Box>
    </div>
  );
}

export default Home;