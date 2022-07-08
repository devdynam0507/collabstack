<script lang="ts">
    import Badge from "./Badge.svelte";
</script>

<main>
    <div class="box-container">
        <div class="box-heading">
            <div class="box-heading-content">
                <div class="box-heading-icon"></div>
                <div class="box-heading-title">이건 카드 타이틀 입니다.</div>
            </div>
            <div>
                <i class="fa fa-regular fa-map-pin" style="cursor: pointer; color: red;"></i>
            </div>
        </div>
        <div class="box-sub-heading">
            <div class="box-sub-heading-name">name</div>
            <div class="box-sub-heading-time">1 hours ago</div>
        </div>
        <div class="box-content">
            <p>
                Amet minim mollit non deserunt ullamcoest 
                sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.
                 Exercitation veniam consequat sunt nostrud amet.
            </p>
        </div>
        <div class='box-footer'>
            <div class="box-badges">
                <Badge badges={["꼼꼼해요!", "커뮤니케이션 능력이 좋아요!"]}/>
            </div>
            <div class="box-like">
                <i class="fa fa-star" style="margin-right: 5px;"></i>
                <p>521</p>
            </div>
        </div>
    </div>
 </main>

<style>
    .box-container {
        border: 0.1px solid rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        margin-bottom: 20px;
        margin-right: 20px;  
        padding: 10px;
    }

    .box-heading {
        display: flex;
        justify-content: space-between;
        margin-top: 5px;
    }

    .box-heading-content {
        display: flex;
    }

    .box-heading-icon {
        width: 16px;
        height: 16px;
        border-radius: 50%;
        background-color: #333;
        margin: 0px 5px 0px 5px;
    }

    .box-heading-title {
        margin: 0px 5px 0px 5px;
        color: rgba(0, 0, 0, 0.7);
    }

    .box-sub-heading {
        margin: 0px 5px 0px 5px;
        display: flex;
        justify-content: space-between;
        font-size:smaller;
    }

    .box-sub-heading-name {
        margin: 5px 5px 0px 5px;
        color: rgba(0, 0, 0, 0.4);
    }

    .box-sub-heading-time {
        margin: 5px 5px 0px 5px;
        color: rgba(0, 0, 0, 0.4);
    }

    .box-content {
        margin: 5px;
    }

    .box-footer {
        display: flex;
        justify-content: space-between;
        margin: 0px 5px 0px 5px;
    }

    .box-badges {
        display: flex;
        align-items: center;
    }

    .box-like {
        display: flex;
        font-size: small;
        align-items: center;
        padding: auto;
    }
</style>