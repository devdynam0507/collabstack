<script lang="ts">

</script>

<main>
    <div class="recommenders-box">
        <div class="recommenders-wrap">
            <div class="recommenders-box-header">Recommenders</div>
            <div class="recommenders-item">
                <div class="recommenders-box-image"></div>
                <div class="recommenders-box-content">Ellen</div>
            </div>
            <div class="recommenders-item">
                <div class="recommenders-box-image"></div>
                <div class="recommenders-box-content">Kelly</div>
            </div>
            <div class="recommenders-item">
                <div class="recommenders-box-image"></div>
                <div class="recommenders-box-content">Holy</div>
            </div>
        </div>
    </div>
</main>

<style>
    .recommenders-box {
        width: 200px;
        height: 150px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        position: absolute;

        left: 29px;
        top: 300px;
        background: rgba(23, 157, 227, 0.2);
    }

    .recommenders-wrap {
        margin: 10px;
    }

    .recommenders-box-header {
        font-family: 'Inter';
        font-style: normal;
        font-weight: 700;
        display: flex;
        color: #333333;
    }

    .recommenders-item {
        display: flex;
        vertical-align: baseline;
        align-items: center;
        margin: 10px 0px 10px;
    }

    .recommenders-box-content {
        vertical-align: baseline;
        margin-left: 10px;
    }

    .recommenders-box-image {
        height: 20px;
        width: 20px;
        background-color: black;
        border-radius: 50%;
        align-self: center;
    }
</style>