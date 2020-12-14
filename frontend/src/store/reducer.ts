import { Cart, Item } from "../models/item";
import { ItemsState } from "./store"

const Reducer = (state: ItemsState, action: any) => {
    switch (action.type) {
        case 'LOAD_ITEMS':
            return {
                ...state,
                items: action.payload
            };
        case 'ADD_ITEM_TO_CART':
            return {
                ...state,
                cart: addItemToCart(copyCart(state.cart), action.payload)
            };
        case 'REMOVE_ITEM_FROM_CART':
            return {
                ...state,
                cart: removeItemFromCart(copyCart(state.cart), action.payload)
            };
        case 'RESET_CART':
            return {
                ...state,
                cart: {
                    items: [], 
                    totalItems: 0, 
                    total: 0
                },
                isCheckout: false,
                isFinished: false,
            };
        case 'FINISH_SALE':
            const total = parseFloat(state.cart.total.toFixed(2));
            return {
                ...state,
                items: action.payload,
                 cart: {
                    total,
                    totalItems: 0,
                    items: [], 
                },
                isCheckout: false,
                isFinished: true,
            };
        case 'CHECKOUT':
            return {
                ...state,
                isCheckout: true
            };
        case 'CHANGE_AMOUNT':
            return {
                ...state,
                amount: action.payload
            };
        default:
            return state;
    }
};

const addItemToCart = (cart: Cart, itemToAdd: Item) => {
    const itemOnCart = cart.items.find(cartItem => cartItem.item.id === itemToAdd.id);
    if (itemOnCart) {
        itemOnCart.quantity += 1;
    } else {
        cart.items.push({item: itemToAdd, quantity: 1});
    }
    cart.total += itemToAdd.price;
    cart.totalItems += 1;
    return cart;
}


const removeItemFromCart = (cart: Cart, itemToRemove: Item): Cart => {
    const itemOnCart = cart.items.find(cartItem => cartItem.item.id === itemToRemove.id);
    if (itemOnCart) {
        if (itemOnCart.quantity === 1) {
            cart.items = cart.items.filter(cartItem => cartItem.item.id !== itemToRemove.id);
        } else {
            itemOnCart.quantity -= 1;
        }
        cart.total -= itemToRemove.price;
        cart.totalItems -= 1;
    }
    return cart;
  }

const copyCart = (cart: Cart): Cart => {
    const newCart = Object.assign({}, cart);
    newCart.items = cart.items.map(item => Object.assign({}, item));
    return newCart;
}

export default Reducer;