import React, { useContext, Fragment } from "react";
import List from "@material-ui/core/List";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import Divider from "@material-ui/core/Divider";
import Box from "@material-ui/core/Box";
import RemoveShoppingCartIcon from "@material-ui/icons/RemoveShoppingCart";
import ShoppingCartIcon from "@material-ui/icons/ShoppingCart";
import { Context } from "../../store/store";
import "./cart.css";
import { processSale } from "../../services/bake-sale-service";
import { useParams } from "react-router-dom";

function CartComponent() {
  const { state, dispatch } = useContext(Context);
  const { bakeSaleId } = useParams<any>();

  const resetCart = () => {
    dispatch({ type: "RESET_CART" });
  };

  const checkout = () => {
    dispatch({ type: "CHECKOUT" });
  };

  const finishSale = async () => {
    const items =  await processSale(bakeSaleId, state.cart.items);
    dispatch({ type: "FINISH_SALE", payload: items });
  };

  const onChangeAmount = (event: any) => {
    const sanitizeString = event.target.value.replace(/[^0-9]/g, '');
    dispatch({ type: "CHANGE_AMOUNT", payload: parseFloat(sanitizeString) });
  }


  const { cart, isCheckout, isFinished, amount } = state;

  return (
    <div>
      <Box display="flex" p={1} flexDirection="row">
        <Box p={1}>
          <Button
            variant="contained"
            color="secondary"
            disabled={cart.totalItems === 0}
            className="button"
            onClick={resetCart}
            startIcon={<RemoveShoppingCartIcon />}
          >
            Reset
          </Button>
        </Box>
        <Box p={1}>
          <Button
            variant="contained"
            color="primary"
            disabled={cart.totalItems === 0}
            className="button"
            onClick={checkout}
            startIcon={<ShoppingCartIcon />}
          >
            Checkout
          </Button>
        </Box>
      </Box>
      <List>
        <ListItem key="Total">
          <ListItemText
            primary={
                <Typography
                  component="span"
                  variant="body2"
                  color="textPrimary"
                >
                  Total: €{cart.total.toFixed(2)} { isFinished && amount ? `- Change: €${(amount - cart.total).toFixed(2)}` : ""}
                </Typography>
                
            }
          />
        </ListItem>
        { isCheckout && ( 
        <ListItem key="Payment">
          <Box display="flex" p={1} flexDirection="row">
            <Box p={1}>
              <TextField
                id="outlined-basic"
                label="Amount"
                variant="outlined"
                size="small"
                onChange={onChangeAmount}
                defaultValue="0"
                error={amount !== undefined && amount < cart.total}
              />
            </Box>
            <Box p={1}>
          <Button
            variant="contained"
            color="primary"
            disabled={cart.totalItems === 0}
            onClick={finishSale}
          >
            Finish
          </Button>
          </Box>
          </Box>
        
        </ListItem>
          )
        }
        { isFinished && ( 
        <ListItem key="New" alignItems="center">
            <Button
              variant="contained"
              color="primary"
              onClick={resetCart}
            >
              New
            </Button>
        
        </ListItem>
          )
        }
        {cart.items.map((cartItem) => (
          <React.Fragment>
            <Divider variant="middle" />
            <ListItem key={cartItem.item.id}>
              <ListItemAvatar>
                <Avatar alt={cartItem.item.name} src={cartItem.item.picture} />
              </ListItemAvatar>
              <ListItemText
                primary={
                  <React.Fragment>
                    <Typography
                      component="span"
                      variant="body2"
                      color="textPrimary"
                    >
                      {cartItem.item.name} ({cartItem.quantity}) - €
                      {(cartItem.item.price * cartItem.quantity).toFixed(2)}
                    </Typography>
                  </React.Fragment>
                }
              />
            </ListItem>
          </React.Fragment>
        ))}
        <Divider variant="middle" />
      </List>
    </div>
  );
}

export default CartComponent;
