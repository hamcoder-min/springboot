import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductAvatar } from './ProductAvatar.jsx';

import { useDispatch, useSelector } from 'react-redux';
import { getProductList } from '../../feature/product/productAPI.js';


export function ProductList() {
    const dispatch = useDispatch();
    const productList = useSelector((state) => state.product.productList);

    // const {productList} =useContext(ProductContext);
    // const {createProduct} = useProduct();
    const [number, setNumber] = useState(3);

    useEffect(() => { 
        //1. createProduct 호출
        // createProduct(number); 
        dispatch(getProductList(number));
    }, [number]);

    return (
        <div>
            {productList && productList.map((rowArray,idx) => 
                <div className='product-list' key={idx}>
                    {rowArray && rowArray.map((product, idx) => 
                        <Link to={`/products/${product.pid}`}>
                            <ProductAvatar img={product.image} key={idx} />
                        </Link>
                    )}
                </div>  
            )}
        </div>
    );
}