export interface MyRoomInfo {
  agreementId: number;
  apartmentId: number;
  roomId: number;
  apartmentName: string;
  roomNumber: string;
  imageUrl: string;
  address: string;
  rent: number;
  leaseStartDate: string;
  leaseEndDate: string;
}

export interface BillItem {
  id: number;
  billType: number;
  billTypeName: string;
  amount: number;
  billingPeriod: string;
  dueDate: string;
  status: number;
  paidTime?: string;
  tradeNo?: string;
}

export interface RepairItem {
  id: number;
  apartmentName: string;
  roomNumber: string;
  description: string;
  contactPhone: string;
  status: number;
  statusName: string;
  createTime: string;
}

export interface CommunityCommentVo {
  id: number;
  postId: number;
  userId: number;
  nickname: string;
  avatarUrl?: string;
  content: string;
  createTime: string;
}

export interface CommunityPostItem {
  id: number;
  userId: number;
  nickname: string;
  avatarUrl?: string;
  title: string;
  content: string;
  likeCount: number;
  commentCount: number;
  createTime: string;
  likedByMe?: boolean;
}

export interface MessageItem {
  id: number;
  title: string;
  content: string;
  messageType: number;
  isRead: number;
  bizId?: number;
  createTime: string;
}
