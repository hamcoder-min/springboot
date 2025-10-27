import { axiosPost } from '../../utils/dataFetch.js';

export const getPayment = async () => {
    //userId, orderId, itemName, totalPrice, ...
    const {userId} = JSON.parse(localStorage.getItem("loginInfo"));
    const url = "/payment/kakao/ready"; //카카오 QR 코드 호출
    const data = {
        "orderId": "1234",
        "userId": userId,
        "itemName": "test",
        "qty": "10",
        "totalAmount": "1000",
    };

    try{
        const kakaoReadyResult = await axiosPost(url, data);
        console.log("kakaoReadyResult --> ", kakaoReadyResult);
    } catch(error) {
        console.log("error :: ", error);
    }
}