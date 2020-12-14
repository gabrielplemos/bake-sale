export interface Item {
    readonly id: string;
    readonly name: string;
    readonly price: number;
    available: number;
    readonly picture: string;
}

export interface CartItem {
    readonly item: Item;
    quantity: number;
}

export interface Cart {
    items: CartItem[];
    totalItems: number;
    total: number;
}