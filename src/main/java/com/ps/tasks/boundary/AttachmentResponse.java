package com.ps.tasks.boundary;

import com.ps.tasks.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AttachmentResponse {
    String filename;
    String comment;

    static AttachmentResponse from(Attachment attachment) {
        return new AttachmentResponse(
                attachment.getFilename(),
                attachment.getComment()
        );
    }
}
