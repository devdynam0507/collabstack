<script lang="ts">
    export let badges: string[];
</script>

{#each badges as badge}
    <div class="badge">{badge}</div>
{/each}

<style>
    @media only screen and (min-width: 1224px) {
        .badge {
            padding: 4px;
            display: inline-block;
            font-weight: bold;

            background: rgba(39, 213, 126, 0.5);
            border-radius: 4px;
            margin-right: 5px;
        }
    }

    @media screen and (min-height: 800px) {
        .badge {
            padding: 4px;
            display: inline-block;
            font-weight: bold;
            font-size: xx-small;
            background: rgba(39, 213, 126, 0.5);
            border-radius: 4px;
            margin-right: 5px;
        }
    }

    @media screen and (max-width: 767px) {
        .badge {
            padding: 4px;
            display: inline-block;
            font-weight: bold;
            font-size: xx-small;
            background: rgba(39, 213, 126, 0.5);
            border-radius: 4px;
            margin-right: 5px;
        }
    }
</style>