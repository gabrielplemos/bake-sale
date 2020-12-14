import React from 'react';
import Store from '../../store/store';
import CartComponent from '../cart/cart';
import Home from '../home/home';
import Items from '../items/items';
import Box from '@material-ui/core/Box';
import './app.css';

function App() {
  return (
    <Store>
      <div style={{ width: '100%' }}>
        <Box display="flex" p={1} flexDirection="row">
          <Box p={1}>
            <Items/>
          </Box>
          <Box p={1} minWidth={250}>
            <CartComponent/>
          </Box>
        </Box>
      </div>
    </Store>
  );
}

export default App;
