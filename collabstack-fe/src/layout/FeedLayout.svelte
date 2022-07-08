<script lang="ts">
    import Card from "../components/Card.svelte";
</script>

<h1 class="title">😎 Feeds</h1>
<p class="sub-title">최근에 올라온 추천이에요!</p>
<div class="feed-container">
    <Card/>
    <Card/>
    <Card/>
    <Card/>
    <Card/>
</div>

<style>
    .feed-container {
        display: flex;
        flex-direction: column;
    }
    
    .sub-title {
        padding: 0px;
        margin: 5px 0px 20px 0px;
        font-size: medium;
        font-weight: 500;
        color: rgba(0, 0, 0, 0.35);
    }

    .title {
        padding: 0px;
        margin: 0px
    }
</style>