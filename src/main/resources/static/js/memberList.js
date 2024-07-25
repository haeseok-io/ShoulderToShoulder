$(() => {

});

const getMemberListData = (request, init = true) => {
    // Val
    let target = $(".memberList");

    // Init
    if( init ) target.html("");

    // Data
    $.get("/member/data", request, data => {
        if( data.total<1 ) {

        } else {
            convertMemberList(data.dataList);
        }
    });
}

const convertMemberList = data => {
    // Val
    let appendSel = $(".memberList");
    let appendHtml = $("#memberListTemplate").html();

    // Data
    data.forEach(obj => {
        let append = $(appendHtml);

        // - 프로필이미지
        let profileImg = obj.profileImg==null ? "/images/profileBlank.png" : obj.profileImg;

        // - 데이터 담기
        append.find(".profileImg img").attr("src", profileImg);
        append.find(".nickname").text(obj.nickname);

        // Result
        appendSel.append(append);
    });
}