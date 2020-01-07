/* tslint:disable */
import { Timestamp } from './timestamp';
export interface AchievementDto {
  collectorId?: string;
  description?: string;
  lastUpdated?: Timestamp;
  name?: string;
  progress?: number;
  userEmail?: string;
  value?: number;
}
