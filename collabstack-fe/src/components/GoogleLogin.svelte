<svelte:head>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
</svelte:head>
<script>
    const { GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, GITHUB_CLIENT_ID, GITHUB_CLIENT_SECRET } = keys;
    window.handleCredentialResponse = (res) => {
        console.log(res);
    }
</script>

<div id="g_id_onload"
data-client_id={GOOGLE_CLIENT_ID}
data-callback="handleCredentialResponse">
</div>
<div class="g_id_signin" data-type="standard"></div>

<style>
    .g_id_signin {
        width: 80%;
        margin: 25px 0px 0px 0px;
        min-width: 184px;
        max-width: 184px;
        align-self: center;
        user-select: none;
        transition: all 400ms ease 0s;
        font-family: Roboto;
    }
</style>