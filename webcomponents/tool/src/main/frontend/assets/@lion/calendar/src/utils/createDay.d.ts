/**
 * @param {Date} date,
 * @returns {import('../../types/day').Day} day
 */
export function createDay(date?: Date, { weekOrder, central, startOfWeek, selected, previousMonth, currentMonth, nextMonth, past, today, future, disabled, }?: {
    weekOrder?: number | undefined;
    central?: boolean | undefined;
    startOfWeek?: boolean | undefined;
    selected?: boolean | undefined;
    previousMonth?: boolean | undefined;
    currentMonth?: boolean | undefined;
    nextMonth?: boolean | undefined;
    past?: boolean | undefined;
    today?: boolean | undefined;
    future?: boolean | undefined;
    disabled?: boolean | undefined;
}): import('../../types/day').Day;
