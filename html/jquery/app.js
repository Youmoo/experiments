var el = $("h1").on("fire", function () {
    console.log(this);
});

//trigger要遍历整个dom tree
el.trigger("fire")
//triggerHandler只针对当前元素集合中的第一个元素
//el.triggerHandler("fire")