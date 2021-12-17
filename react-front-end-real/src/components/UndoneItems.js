import { FrownOutlined } from '@ant-design/icons/lib/icons';
import { useSelector } from 'react-redux';

function UndoneItems() {
    const todoItems = useSelector(state => state.todoItems);

    return (
        <div className="main-page center-text">
            <h1 className="center-item "><FrownOutlined /> Undone List <FrownOutlined /></h1>
            <div className="center-item">
            {
                todoItems.filter(item => !item.done).length > 0 ? 
                todoItems.filter(item => !item.done).map((item, index) => {
                   return <p className='todo-item-display todo-item-display-undone' key={item+index}>{item.text}</p>
                }) :
                <p className="list-empty-label">輕輕鬆鬆又放工~</p>
            }
            </div>
        </div>
    );
}

export default UndoneItems;