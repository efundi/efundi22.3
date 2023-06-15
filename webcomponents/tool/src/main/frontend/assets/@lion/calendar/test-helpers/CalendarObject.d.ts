/**
 * Abstraction around calendar DOM structure,
 * allows for writing readable, 'DOM structure agnostic' tests
 */
export class CalendarObject {
    /**
     * @param {import('../src/LionCalendar').LionCalendar} calendarEl
     */
    constructor(calendarEl: import('../src/LionCalendar').LionCalendar);
    el: import("../src/LionCalendar.js").LionCalendar;
    /**
     * Node references
     */
    get rootEl(): Element | null | undefined;
    get headerEl(): Element | null | undefined;
    get yearHeadingEl(): Element | null | undefined;
    get monthHeadingEl(): Element | null | undefined;
    get nextYearButtonEl(): HTMLElement & {
        ariaLabel: string;
    };
    get previousYearButtonEl(): HTMLElement & {
        ariaLabel: string;
    };
    get nextMonthButtonEl(): Element | undefined;
    get previousMonthButtonEl(): Element | undefined;
    get gridEl(): Element | null | undefined;
    get weekdayHeaderEls(): HTMLElement[];
    get dayEls(): HTMLElement[];
    get previousMonthDayEls(): HTMLElement[];
    get nextMonthDayEls(): HTMLElement[];
    get dayObjs(): DayObject[];
    get previousMonthDayObjs(): DayObject[];
    get nextMonthDayObjs(): DayObject[];
    /**
     * @param {number} monthDayNumber
     */
    getDayEl(monthDayNumber: number): HTMLElement;
    /**
     * @param {number} monthDayNumber
     */
    getDayObj(monthDayNumber: number): DayObject;
    get selectedDayObj(): DayObject | undefined;
    get centralDayObj(): DayObject | undefined;
    get focusedDayObj(): DayObject | undefined;
    /**
     * @desc Applies condition to all days, or days in filter
     *
     * @param {function} condition : condition that should apply for "filter" days
     * - Example: "(dayObj) => dayObj.selected"
     * @param {number[]|function} [filter] - month day numbers for which condition should apply.
     * - Example 1: "[15, 20]"
     * - Example 2: "(dayNumber) => dayNumber === 15" (1 based ,not zero based)
     */
    checkForAllDayObjs(condition: Function, filter?: Function | number[] | undefined): boolean;
    /**
     * States
     */
    get activeMonth(): string | undefined;
    get activeYear(): string | undefined;
}
import { DayObject } from "./DayObject.js";
