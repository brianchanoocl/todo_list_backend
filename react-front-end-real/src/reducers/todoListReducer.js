import { UPDATE_TODOITEM_DONE_STATUS, UPDATE_TODOLIST, REMOVE_TODOITEM, INIT_TODO, UPDATE_TODOITEM_DETAIL } from "../constants/constants";

const initState = { todoItems: [] };

const todoListReducer = ( state = initState, action ) => {
    switch(action.type) {
        case INIT_TODO:
            state.todoItems = [...state.todoItems, []];
            return {todoItems:  action.payload};
        case UPDATE_TODOLIST:
            return {todoItems: [...state.todoItems, action.payload]};
        case UPDATE_TODOITEM_DETAIL:
            return { todoItems: state.todoItems.map(item => {
                if(item.id === action.payload.id) {
                    item = action.payload;
                }
                return item;
            })};
        case UPDATE_TODOITEM_DONE_STATUS:
            return { todoItems: state.todoItems.map(item => {
                if(item.id === action.payload.id) {
                    item = action.payload;
                }
                return item;
            })};
        case REMOVE_TODOITEM:
            return {todoItems: state.todoItems.filter(item => item.id !== action.payload.id)};
        default:
            return state;
    }
};

export default todoListReducer;