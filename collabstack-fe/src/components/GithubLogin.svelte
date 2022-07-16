<script lang="ts">
    import { onMount } from 'svelte';
    const { GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET } = keys;
    const GITHUB_OAUTH2_URL = `https://github.com/login/oauth/authorize?client_id=${GITHUB_CLIENT_ID}`;

    onMount(async () => {
        const urlParams = new URLSearchParams(window.location.search);
        const hasCode = urlParams.has('code');
        
        if (hasCode) {
            const code: string = urlParams.get('code');
            console.log(code);
            // 서버로 보내기
        }
    });

    function onClickGithubButton() {
        location.href = GITHUB_OAUTH2_URL;
    }

</script>

<div class="github-wrapper">
    <div class="github-btn" on:click={onClickGithubButton}>
        <i class="fa fa-github" style="margin-left: 12px; font-size: x-large;"></i>
        <p class="btn-text"><b>Sign in with Github</b></p>
    </div>
</div>

<style>
    .github-wrapper {
        padding: 2px 10px;
        display: flex;
        align-self: center;
    }

    .github-btn {
        display: flex;
        margin: 25px 0px 0px 10px;
        width: 100%;
        min-width: 193px;
        max-width: 193px;
        height: 38px;
        align-self: center;
        border: 1px solid rgba(51, 51, 51, 0.1);
        border-radius: 2px;
        box-shadow: 0 3px 4px 0 rgba(0, 0, 0, .2);
        background-color: #fcfcfc;
        align-items: center;
        cursor: pointer;
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