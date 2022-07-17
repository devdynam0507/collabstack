<script>
</script>

<main>
    <div class="profile">
        <div class="profile-picture"></div>
        <div class="profile-name">남대영</div>
        <div class="profile-info-box">
            <i class="gg-organisation" style="opacity: 0.8;"></i>
            <div class="profile-company">kakao</div>
        </div>
        <div class="profile-info-box">
            <i class="fa fa-github" style="font-size:24px; opcity: 0.8"></i>
            <a href="https://naver.com" class="profile-url" target="_blank">https://github.com</a>
        </div>
    </div>
</main>

<style>
    @media only screen and (max-width: 767px) {
        .profile {
            width: 80%;
            margin-left: 20px;
        }
    }

    @media only screen and (min-width: 1224px) {
        .profile {
            width: 240px;
            height: 240px;
            margin-left: 20px;
        }
    }

    .profile-picture {
        height: 150px;
        width: 150px;
        background-color: #bbb;
        border-radius: 50%;
        align-self: center;
    }

    .profile-name {
        margin-top: 10px;
        font-size: x-large;
        font-weight: bold;
    }

    .profile-info-box {
        display: flex;
        font-weight: bold;
        justify-content: space-between;
        margin-top: 10px;
        text-decoration: none;
    }

    .profile-company {
        align-self: center;
        opacity: 0.8;
        font-weight: 450;
    }

    .profile-url {
        cursor: pointer;
        font-weight: 450;
    }

    a {
        color: gray;
        text-decoration: none !important;
    }

    a:hover {
        opacity: 0.8;
        text-decoration: none;
        cursor: pointer;
    }
</style>