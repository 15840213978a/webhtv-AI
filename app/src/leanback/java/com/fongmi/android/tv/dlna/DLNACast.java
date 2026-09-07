private String buildMetaData() {
    try {
        DIDLContent content = new DIDLContent();

        VideoItem item = new VideoItem(
                "0",
                "-1",
                video.name,
                "",
                new Res(
                        new ProtocolInfo("http-get:*:video/*:*"),
                        0L,
                        video.url
                )
        );

        content.addItem(item);

        return new DIDLParser().generate(content);

    } catch (Exception e) {
        return "";
    }
}
