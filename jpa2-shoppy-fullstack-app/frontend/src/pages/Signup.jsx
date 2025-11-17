import React, { useRef, useState, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { validateSignupFormCheck } from '../utils/validate.js';
import { initForm } from '../utils/init.js';
import { axiosPost } from '../utils/dataFetch.js';
import { getSignup, getIdCheck } from "../feature/auth/authAPI.js";

export function Signup() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const initArray = ['id', 'pwd', 'cpwd', 'name', 'phone', 'emailName', 'emailDomain'];
    // const initForm = initArray.reduce((acc, cur) => {    //비동기
    //     acc[cur] = "";
    //     return acc;
    // }, {});


    const refs = useMemo(() => {    //Hooks 비동기식 처리 진행
        return initArray.reduce((acc, cur) => {
            acc[`${cur}Ref`] = React.createRef();
            return acc;
        }, {});
    });
    
    const [form, setForm] = useState(initForm(initArray));
    const [errors, setErrors] = useState({...initForm(initArray), emailDomain: ""});

    const handleChangeForm = (e) => {
        const { name, value } = e.target;
        setForm({...form, [name]: value});
        setErrors({...initForm(initArray), emailDomain: ""});
    }
    
    const handleResetForm = () => {
        setForm(initForm(initArray));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const param = { refs: refs, setErrors: setErrors }
        const formData= {...form, email: form.emailName.concat('@', form.emailDomain)}
        const result = await dispatch(getSignup(formData, param));
        console.log('result-> ', result);
        if(result) {
            alert("회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.");
            //로그인 페이지 이동!
            navigate("/login");
        } else alert("회원가입에 실패하셨습니다. 다시 시도해주세요.");
    }   //handleSubmit

    /** 아이디 중복 체크 */
    const handleDuplicateIdCheck = async () => {
        const result = await dispatch(getIdCheck(form.id));
        alert(result);
    }

    return (
    <div className="content">
        <div className="join-form center-layout">
            <h1 className="center-title">회원가입(React)</h1>
            <form onSubmit={handleSubmit}>
                <ul>
                    <li>
                        <label><b>아이디</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.id}</span>
                        <div>
                            <input type="text" 
                                    name="id"   
                                    value={form.id} 
                                    onChange={handleChangeForm}
                                    ref={refs.idRef}                    
                                    placeholder = "아이디 입력(6~20자)" />
                            <button type="button" onClick={handleDuplicateIdCheck}> 중복확인</button>
                            <input type="hidden" id="idCheckResult" value="default" />
                        </div>
                    </li>
                    <li>
                        <label><b>비밀번호</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.pwd}</span>
                        <div>
                            <input type="password" 
                                    name="pwd"
                                    value={form.pwd}
                                    onChange={handleChangeForm}
                                    ref={refs[`${initArray[1]}Ref`]}
                                    placeholder="비밀번호 입력(문자,숫자,특수문자 포함 6~12자)"/>
                        </div>
                    </li>
                    <li>
                        <label><b>비밀번호 확인</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.cpwd}</span>
                        <div>
                            <input type="password" 
                                    name="cpwd"
                                    value={form.cpwd}
                                    onChange={handleChangeForm}
                                    ref={refs.cpwdRef}
                                    placeholder="비밀번호 재입력"/>
                        </div>
                    </li>
                    <li>
                        <label><b>이름</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.name}</span>
                        <div>
                            <input type="text" 
                                    name="name"
                                    value={form.name}
                                    onChange={handleChangeForm}
                                    ref={refs.nameRef}
                                    placeholder="이름을 입력해주세요"/>
                        </div>
                    </li>
                    <li>
                        <label><b>전화번호</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.phone}</span>
                        <div>
                            <input type="text" 
                                    name="phone"
                                    value={form.phone}
                                    onChange={handleChangeForm}
                                    ref={refs.phoneRef}
                                    placeholder="휴대폰 번호 입력('-' 포함)"/>
                        </div>
                    </li>
                    <li>
                        <label><b>이메일 주소</b></label>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.emailName}</span>
                        <span style={{color:"red", fontSize:"0.8rem"}}>{errors.emailDomain}</span>
                        <div>
                            <input type="text" 
                                    name="emailName"
                                    value={form.emailName}
                                    onChange={handleChangeForm}
                                    ref={refs.emailNameRef}
                                    placeholder="이메일 주소"/>
                            <span>@</span>       
                            <select name="emailDomain"
                                    value={form.emailDomain}
                                    onChange={handleChangeForm} 
                                    ref={refs.emailDomainRef}
                                    >
                                <option value="default">선택</option>
                                <option value="naver.com">naver.com</option>
                                <option value="gmail.com">gmail.com</option>
                                <option value="daum.net">daum.net</option>
                            </select>
                        </div>
                    </li>
                    <li>
                        <button type="submit">가입하기</button>
                        <button type="reset"
                                onClick={handleResetForm}>다시쓰기</button>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    );
}