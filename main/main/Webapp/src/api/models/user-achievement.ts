/* tslint:disable */
import { Timestamp } from './timestamp';
export interface UserAchievement {
  achievementId?: string;
  collectorId?: string;
  lastUpdated?: Timestamp;
  progress?: number;
  userEmail?: string;
}
