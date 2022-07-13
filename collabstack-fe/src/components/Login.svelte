<script>
</script>

<div class="wrapper">
    <div class="login-box">
        <p class="login-title">Welcome Collabstack !</p>
        <p class="login-description">간편로그인으로 아주 쉽게 협업 리뷰 관리를 시작하세요!</p>

        <slot name="google"/>
        <slot name="github"/>
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
        width: 25%;
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
</style>