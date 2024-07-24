$(() => {
    // 모임 상세 보기

    // 모임 좋아요
});

const getMoimListData = (request, init = true) => {
    // Val
    let target = $("#moimList");

    // Init
    if( init )  target.html("");

    // Data
    $.ajax({
        type: "GET",
        url: "/moim/data",
        data: request,
        dataType: "json",
        success: data => {
            if( data.total<1 ) {

            } else {
                convertMoimList(data.dataList);
            }
        }
    });
}

const convertMoimList = data => {
    // Val
    let appendSel = $("#moimList");
    let appendHtml = $("#moimListTemplate").html();

    // Data
    data.forEach(obj => {
        let append = $(appendHtml);

        // - 타입
        let typeName = obj.type==="P" ? "프로젝트" : "스터디";
        let typeClass = obj.type==="P" ? "main" : "active";

        // - 썸네일
        let thumbnailPath = obj.thumbnail!=null && obj.thumbnail!=="" ? "/display?fileName="+obj.thumbnail : "";

        // - 상태
        let statusName = obj.status==="W" ? "모집중" : "모집완료";

        // - 데이터 담기
        append.attr("data-type", obj.type);
        append.attr("data-no", obj.no);
        append.attr("data-thumbnail", true);
        append.find(".thumbnail img").attr("src", thumbnailPath);
        append.find(".category").text(obj.category.name);
        append.find(".type span").addClass("badge-"+typeClass).text(typeName);
        append.find(".heart span").text(obj.heart);
        append.find(".subject").text(obj.subject);
        append.find(".explanation").text(obj.explanation);
        append.find(".headcountToggle .toggleName span:nth-of-type(1)").text(statusName);
        append.find(".headcountToggle .personnel span:nth-of-type(1)").text(obj.headcountList.reduce((sum, item) => sum + item.approvedCount, 0));
        append.find(".headcountToggle .personnel span:nth-of-type(2)").text(obj.headcountList.reduce((sum, item) => sum + item.personnelCount, 0));

        // - 썸네일
        if( thumbnailPath==="" ) {
            append.attr("data-thumbnail", false);
            append.find(".thumbnail").hide();
        }

        // - 사용언어/기술
        obj.languageList.forEach(language => {
            append.find(".languageList").append(`<li><span class="badge badge-size-sm badge-white_opacity">#${language.name}</span></li>`);
        });

        // - 모집 인원
        let headcountSel = append.find(".headcountList ul");
        let headcountHtml = headcountSel.html();

        headcountSel.html("");

        obj.headcountList.forEach(headcount => {
            let appendHtml = $(headcountHtml);

            appendHtml.find(".positionDetail").text(headcount.positionDetail.name);
            appendHtml.find(".personnel span:nth-of-type(1)").text(headcount.approvedCount);
            appendHtml.find(".personnel span:nth-of-type(2)").text(headcount.personnelCount);

            headcountSel.append(appendHtml);
        });

        // Result
        appendSel.append(append);
    });
}