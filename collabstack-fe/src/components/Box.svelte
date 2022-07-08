<script lang="ts">
</script>

<div class="wrapper">
    <div class="login-box">
        <p class="login-title">Welcome Collabstack !</p>
        <p class="login-description">간편로그인으로 아주 쉽게 협업 리뷰 관리를 시작하세요!</p>

        <div class="google-btn">
            <div class="google-icon-wrapper">
              <img class="google-icon-svg" src="https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg"/>
            </div>
            <p class="btn-text"><b>Sign in with Google</b></p>
        </div>
        <div class="github-btn">
            <i class="fa fa-github" style="margin-left: 12px; font-size: x-large;"></i>
            <p class="btn-text"><b>Sign in with Github</b></p>
        </div>
    </div>
</div>

<style>
    .wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background: rgba(51, 51, 51, 0.05)
    }

    /* Login */
    .login-box {
        display: flex;
        flex-direction: column;
        width: 22%;
        height: 50%;
        padding: 5%;

        background-color: #F5F5F5;
        box-shadow: 0px 1px 20px rgba(0, 0, 0, 0.25);
        border-radius: 5px;
        justify-content: center;
    }

    .login-title {
        margin: 0;
        text-align: center;
        font-size: x-large;
        color: #0078F0;
    }

    .login-description {
        text-align: center;
        font-size: medium;
        color: gray;
    }

    .google-btn {
        margin: 25px;
        width: 80%;
        min-width: 184px;
        max-width: 184px;
        height: 42px;
        background-color: #fcfcfc;
        border-radius: 2px;
        box-shadow: 0 3px 4px 0 rgba(0, 0, 0, .2);
        cursor: pointer;
        cursor: hand;
        align-self: center;
        user-select: none;
        transition: all 400ms ease 0s;
        display: flex;
    }

    .google-btn .google-icon-wrapper {
        position: absolute;
        margin-top: 1px;
        margin-left: 1px;
        width: 40px;
        height: 40px;
        border-radius: 2px;
        user-select: none
    }

    .google-btn .google-icon-svg {
        position: absolute;
        margin-top: 11px;
        margin-left: 11px;
        width: 18px;
        height: 18px;
        user-select: none
    }

    .google-btn .btn-text {
        float: right;
        margin: 11px 14px 40px 40px;
        color: #757575;
        font-size: 14px;
        letter-spacing: .2px;
        font-family: Roboto;
        user-select: none
    }

    .google-btn:hover {
        box-shadow: 0 3px 8px rgba(117, 117, 117, .5);
        user-select: none
    }

    .google-btn:active {
        box-shadow: 0 1px 1px #757575;
        background: #F8F8F8;
        color: #fff;
        user-select: none
    }

    .github-btn {
        display: flex;
        padding: 0;
        margin: 0;
        width: 100%;
        min-width: 184px;
        max-width: 184px;
        height: 42px;
        align-self: center;
        border: 1px solid rgba(51, 51, 51, 0.1);
        border-radius: 2px;
        box-shadow: 0 3px 4px 0 rgba(0, 0, 0, .2);
        background-color: #fcfcfc;
        align-items: center;
        cursor: pointer;
        cursor: hand;
    }

    .github-btn .github-icon i {
        width: 0%;
    }

    .github-btn .btn-text {
        padding: 0;
        margin-left: 12px;
        color: #757575;
        font-size: 14px;
        letter-spacing: .2px;
        font-family: Roboto;
        user-select: none;
        /* margin: 11px 14px 40px 40px; */
        letter-spacing: .2px;
        align-self: center;
    }

    .github-btn:hover {
        box-shadow: 0 3px 8px rgba(117, 117, 117, .5);
        user-select: none
    }

    .github-btn:active {
        box-shadow: 0 1px 1px #757575;
        background: #F8F8F8;
        color: #fff;
        user-select: none
    }
</style>