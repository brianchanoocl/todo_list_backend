import { LikeOutlined, SmileOutlined } from '@ant-design/icons/lib/icons';
import { useSelector } from 'react-redux';

function DoneItems() {
    const todoItems = useSelector(state => state.todoItems);

    return (
        <div className="main-page center-text">
            <h1 className="center-item"><SmileOutlined /> Done List <SmileOutlined /></h1>
            <div className="center-item">
            {
                todoItems.filter(item => item.done).length > 0 ? 
                todoItems.filter(item => item.done).map((item, index) => {
                   return <p className='todo-item-display todo-item-display-done' key={item+index}>{item.text}</p>
                }) :
                <p className="list-empty-label rainbow">堅守做人宗旨，可以hea的話不會郁 <LikeOutlined /></p>
            }
        </div>
        </div>
    );
}

export default DoneItems;