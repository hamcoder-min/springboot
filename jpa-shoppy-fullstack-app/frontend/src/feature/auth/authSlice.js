import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    isLogin: false
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login(state, action) {
            state.isLogin = !state.isLogin;
            const {userId} = action.payload;
            const loginInfo = {"userId": userId};
            localStorage.setItem("loginInfo", JSON.stringify(loginInfo));
        },
        logout(state) {
            state.isLogin = !state.isLogin;
            localStorage.removeItem("loginInfo");
        }
    },
})

// Action creators are generated for each case reducer function
export const { login, logout } = authSlice.actions //컴포넌트 또는 API 함수에서 dispatch(액션함수)

export default authSlice.reducer //store에서 호출(import)