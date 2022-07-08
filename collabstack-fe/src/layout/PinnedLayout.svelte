<script lang="ts">
    import Card from "../components/Card.svelte";
</script>

<h1>📌 Pinned</h1>
<div class="content-pinned">
    <Card/>
    <Card/>
    <Card/>
    <Card/>
</div>

<style>
    @media only screen and (max-width: 767px) {
        .content-pinned {
            gap: 5px;
        }
    }

    @media only screen and (min-width: 1224px) {
        .content-pinned {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 5px;
        }
    }
</style>