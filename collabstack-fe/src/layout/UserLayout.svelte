<script>

</script>

<div class="layout">
    <div class="profile-layout">
        <slot name="profile"/>
    </div>
    <div class="content-layout">
        <slot name="pinned"/>
        <slot name="feed"/>
    </div>
</div>

<style>
    @media only screen and (max-width: 767px) {
        .layout {
            display: flex;
            flex-direction: column;
            width: 100%;
            height: 100%;
        }
    }

    @media only screen and (min-width: 1224px) {
        .layout {
            display: flex;
            width: 100%;
            height: 100%;
            padding: 10px;
        }
    }

    .layout {
        display: flex;
        width: 100%;
        height: 100%;
        padding: 10px;
    }

    .profile-layout {
        display: flex;
        flex-direction: column;
        padding: 10px;
        margin: 20px;
    }

    @media only screen and (max-width: 767px) {
        .content-layout {
            width: 100%;
            display: flex;
            flex-direction: column;
            padding: 10px;
        }
    }

    @media only screen and (min-width: 1224px) {
        .content-layout {
            width: 50%;
            display: flex;
            flex-direction: column;
            padding: 10px;
            margin: 20px;
        }
    }
</style>