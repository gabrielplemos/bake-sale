import React, {useContext, useEffect} from 'react';
import { Item } from '../../models/item';
import { getItems } from '../../services/bake-sale-service';
import './items.css';
import GridList from '@material-ui/core/GridList';
import GridListTile from '@material-ui/core/GridListTile';
import GridListTileBar from '@material-ui/core/GridListTileBar';
import IconButton from '@material-ui/core/IconButton';
import RemoveIcon from '@material-ui/icons/Remove';
import { Context } from '../../store/store';
import { useParams } from "react-router-dom";


function Items() {
  const {state, dispatch} = useContext(Context);
  const { bakeSaleId } = useParams<any>();
  const { items, isCheckout, isFinished } = state;

  const addItemToCart = (itemToAdd: Item) => {
    if(itemToAdd.available > 0 && !isCheckout && !isFinished ) {
        dispatch({type:"ADD_ITEM_TO_CART", payload: itemToAdd});
    }
  }

  const removeItemFromCart = (itemToRemove: Item) => {
    if(!isCheckout && !isFinished ) {
        dispatch({type:"REMOVE_ITEM_FROM_CART", payload: itemToRemove});
    }
  }

  useEffect(() => { 
    const fetchItems = async () => {
        try {
            const items = await getItems(bakeSaleId);
            dispatch({type: 'LOAD_ITEMS', payload: items})
        } catch (e) {
            console.log(e);
        }
    };
    fetchItems();
  }, []);

  return (
    <div>
      <GridList cellHeight={180}>
        {items.map((item) => (
          <GridListTile key={item.id}>
            <img src={item.picture} alt={item.name} onClick={() => addItemToCart(item)} className={`${item.available === 0 ? "image-greyed-out" : ""}`}/>
            <GridListTileBar
              title={<span>{item.name}: €{item.price}</span>}
              subtitle={<span>Available: {item.available}</span>}
              actionIcon={
                <IconButton aria-label={`remove ${item.name}`} color="secondary" onClick={() => removeItemFromCart(item)}>
                  <RemoveIcon />
                </IconButton>
              }
            />
          </GridListTile>
        ))}
      </GridList>
    </div>
  );
}

export default Items;