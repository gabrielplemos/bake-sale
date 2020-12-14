import React, {createContext, useCallback, useReducer} from "react";
import { Cart, Item } from "../models/item";
import Reducer from './reducer'

export interface StoreProps {
    children?: any;
  }

interface ItemsContext {
    state: ItemsState;
    dispatch: React.Dispatch<any>;
}

export interface ItemsState {
    items: ReadonlyArray<Item>;
    cart: Cart;
    isCheckout: boolean;
    isFinished: boolean;
    amount?: number;
}

const initialState = {
    items: [], 
    cart:{
        items: [], 
        totalItems: 0, 
        total: 0
    },
    isCheckout: false,
    isFinished: false,
}


export const Context = createContext<ItemsContext>({
    state: initialState,
    dispatch: () => null
  });

const Store = (props: StoreProps) => {
    const [state, dispatch] = useReducer(Reducer, initialState);
    return (
        <Context.Provider value={{state, dispatch}}>
            {props.children}
        </Context.Provider>
    )
};

export default Store;