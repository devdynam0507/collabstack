<script>
	import Login from "../components/Login.svelte";
    import GoogleLogin from "../components/GoogleLogin.svelte";
    import GithubLogin from "../components/GithubLogin.svelte";
</script>

<Login>
    <GoogleLogin slot="google"/>
    <GithubLogin slot="github"/>
</Login>