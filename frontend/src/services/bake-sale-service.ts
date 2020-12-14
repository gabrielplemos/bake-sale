import axios from 'axios';
import { CartItem, Item } from '../models/item';

export const loadItems = async (items: any): Promise<void> => {
    const response = await axios.post('http://localhost:8080/v1/bake-sales', {items});
    return response.data.id;
};


export const getItems = async (bakeSaleId: string): Promise<ReadonlyArray<Item>> => {
    const response = await axios.get(`http://localhost:8080/v1/bake-sales/${bakeSaleId}`);
    return response.data.items.map((item: any) => ({...item, picture: item.image_url}));
};


export const processSale = async (bakeSaleId: string, cartItems: CartItem[]): Promise<void> => {
    const sale = {
        bake_sale_id: bakeSaleId, 
        items: cartItems.map(cartItem => ({
            item_id: cartItem.item.id,
            quantity: cartItem.quantity
        }))
    };
    const response = await axios.post(`http://localhost:8080/v1/bake-sales/${bakeSaleId}/sale`, sale);
    return response.data.items.map((item: any) => ({...item, picture: item.image_url}));
};