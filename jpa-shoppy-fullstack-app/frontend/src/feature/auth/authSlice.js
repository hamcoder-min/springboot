import { createSlice } from '@reduxjs/toolkit'

const saveAuth = JSON.parse(localStorage.getItem("auth"));

const initialState = saveAuth || {
    isLogin: false,
    role: ''
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login(state, action) {
            const { userId, role } = action.payload;
            state.isLogin = !state.isLogin;
            state.role = role;

            const loginInfo = {"userId": userId};
            console.log("userID", userId, role);
            localStorage.setItem("loginInfo", JSON.stringify(loginInfo));

            //새로고침을 위한 데이터 복사(localStorage 저장)
            localStorage.setItem("auth", JSON.stringify({ isLogin: true, userId, role })) //"isLogin": isLogin -> 이름이 같을 경우 앞쪽 "isLogin": 을 생략 가능

        },
        logout(state) {
            state.isLogin = !state.isLogin;

            //로컬스토리지 정보 삭제
            localStorage.removeItem("loginInfo");
            localStorage.removeItem("auth");
            localStorage.removeItem("cart");
        }
    },
})

// Action creators are generated for each case reducer function
export const { login, logout } = authSlice.actions //컴포넌트 또는 API 함수에서 dispatch(액션함수)

export default authSlice.reducer //store에서 호출(import)