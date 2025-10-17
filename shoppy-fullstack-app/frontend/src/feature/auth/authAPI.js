import { login, logout } from "./authSlice.js"
import { validateFormCheck, validateSignupFormCheck } from "../../utils/validate.js";
import { axiosPost } from "../../utils/dataFetch.js";

/**
    ID 중복 체크
*/
export const getIdCheck = (id) => async (dispatch) => {
    const data = { "id": id };
    const url = "http://localhost:8080/member/idcheck";
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
        const url="http://localhost:8080/member/signup"
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
        const url = "http://localhost:8080/member/login";
        const result = await axiosPost(url, formData);
        if(result) {
            dispatch(login({"userId": formData.id}));
            return true;
        }
    }
    return false;
}

export const getLogout = () => async (dispatch) => {
    dispatch(logout());
    return true;
}