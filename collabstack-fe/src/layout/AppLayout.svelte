<script>
    import NavbarLayout from "./NavbarLayout.svelte";
    import UserLayout from "./UserLayout.svelte";
    import Profile from "../components/Profile.svelte";
    import Recommenders from "../components/Recommenders.svelte";
    import PinnedLayout from "./PinnedLayout.svelte";
    import FeedLayout from "./FeedLayout.svelte";
</script>

<NavbarLayout>
    <UserLayout slot="layout">
		<Profile slot="profile"/>
		<Recommenders slot="recommenders"/>
		<PinnedLayout slot="pinned"/>
		<FeedLayout slot="feed"/>
	</UserLayout>
</NavbarLayout>