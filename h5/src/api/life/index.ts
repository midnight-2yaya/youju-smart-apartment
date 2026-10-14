import http from "@/utils/http";
import type {
  BillItem,
  CommunityPostItem,
  MessageItem,
  MyRoomInfo,
  RepairItem,
  CommunityCommentVo
} from "./types";

export const getMyRoom = () => http.get<MyRoomInfo | null>("/app/myRoom/info");
export const getBills = (billType?: number) =>
  http.get<BillItem[]>("/app/myRoom/bill/list", { billType });
export const simulatePay = (id: number) =>
  http.post<string>(`/app/myRoom/bill/simulatePay?id=${id}`);
export const getRepairs = () =>
  http.get<RepairItem[]>("/app/myRoom/repair/list");
export const submitRepair = (data: {
  description: string;
  contactPhone: string;
}) => http.post("/app/myRoom/repair/submit", data);

export const getCommunityPosts = () =>
  http.get<CommunityPostItem[]>("/app/community/list");
export const getCommunityPostsByPage = (page = 1, size = 10) =>
  http.get<CommunityPostItem[]>("/app/community/page", { page, size });
export const publishCommunityPost = (data: {
  title: string;
  content: string;
}) => http.post("/app/community/publish", data);
export const likeCommunityPost = (id: number) =>
  http.post(`/app/community/like?id=${id}`);
export const unlikeCommunityPost = (id: number) =>
  http.post(`/app/community/unlike?id=${id}`);
export const getPostComments = (postId: number) =>
  http.get<CommunityCommentVo[]>("/app/community/comment/list", { postId });
export const addPostComment = (postId: number, content: string) =>
  http.post("/app/community/comment/add", null, {
    params: { postId, content }
  });
export const deleteCommunityPost = (id: number) =>
  http.post(`/app/community/delete?id=${id}`);

export const getMessages = () => http.get<MessageItem[]>("/app/message/list");
export const getUnreadCount = () =>
  http.get<number>("/app/message/unreadCount");
export const markMessageRead = (id: number) =>
  http.post(`/app/message/read?id=${id}`);
export const markAllMessagesRead = () => http.post("/app/message/readAll");
