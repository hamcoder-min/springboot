import { login, logout } from "./authSlice.js"
import { validateFormCheck, validateSignupFormCheck } from "../../utils/validate.js";
import { axiosPost } from "../../utils/dataFetch.js";
import { getCartCount } from '../../feature/cart/cartAPI.js';
import { updateCartCount, resetCartCount } from '../../feature/cart/cartSlice.js';

/**
    ID 중복 체크
*/
export const getIdCheck = (id) => async (dispatch) => {
    const data = { "id": id };
    const url = "/member/idcheck";
    const result = await axiosPost(url, data);
    return result;
}

/**
    Signup
*/
export const getSignup = (formData, param) => async (dispatch) => {
    let result = null;
    if(validateSignupFormCheck(param)) {
        /* 스프링부트 연동 - Post, /member/signup */
        const url="/member/signup"  //package.json에서 프록시 설정 시 상대경로로 설정(맥에서 충돌남)
        result = await axiosPost(url, formData);
    }
    return result;
}

/**
    Login
*/
export const getLogin = (formData, param) => async (dispatch) => {
    if(validateFormCheck(param)) {
        /**
            SpringBoot - @RestController, @PostMapping("/member/login")
            axios api
        */
        const url = "/member/login";
        const result = await axiosPost(url, formData);
        if(result) {
            dispatch(login({"userId": formData.id}));
            //장바구니 카운트 함수 호출
//            const count = await getCartCount(formData.id);
            //cartSlice > updateCartCount : dispatch 호출
            dispatch(getCartCount(formData.id));
            return true;
        }
    }
    return false;
}

/**
    Logout
*/
export const getLogout = () => async (dispatch) => {
    dispatch(logout());
    dispatch(resetCartCount());
    return true;
}