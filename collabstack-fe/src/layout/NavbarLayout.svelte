<script lang="ts">
</script>

<div class="nav">
    <div class="nav-fonts">Logo</div>
    <div class="nav-fonts">로그아웃</div>
</div>
<slot name="layout"/>

<style>
    .nav {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 70px;
        display: flex;
        background-color: rgba(20, 155, 252, 0.9);
        justify-content: space-between;
    }

    .nav-fonts {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 600;
        font-size: normal;
        text-transform: capitalize;
        align-self: center;
        color: white;
        padding: 20px;
    }
</style>